#!/usr/bin/env python3
import json
import os
import sys
import uuid
from pathlib import Path
from urllib import error, request

API = "https://api.modrinth.com/v2"
TOKEN = os.environ["MODRINTH_TOKEN"]
HEADERS = {
    "Authorization": TOKEN,
    "User-Agent": "ludwici/CopperDeco GitHub Actions (https://github.com/ludwici/CopperDeco)",
    "Accept": "application/json",
}


def api_json(method, path, body=None, content_type=None):
    headers = dict(HEADERS)
    if content_type:
        headers["Content-Type"] = content_type

    req = request.Request(API + path, data=body, headers=headers, method=method)

    try:
        with request.urlopen(req, timeout=35) as response:
            return json.load(response)
    except error.HTTPError as exc:
        detail = exc.read().decode("utf-8", "replace")[:1500]
        raise RuntimeError(f"Modrinth API {method} {path} failed: HTTP {exc.code} {detail}") from exc


def create_project(slug):
    payload = {
        "slug": slug,
        "title": "Copper Deco",
        "description": "Decorative copper tiles for floors, walls and ceilings.",
        "body": (
            "Copper Deco adds 32 decorative copper tile variants for Minecraft 26.2, "
            "including four copper styles, four stages of oxidation and waxed versions. "
            "Tiles may be placed on floors, walls and ceilings. Scrape them with an axe, "
            "wax them with honeycomb or clean oxidation using lightning.\n\n"
            "Requires [Carpets Core](https://modrinth.com/mod/carpets-core). "
            "Source: https://github.com/ludwici/CopperDeco\n"
        ),
        "categories": ["decoration"],
        "additional_categories": [],
        "license_id": "MIT",
        "project_type": "mod",
        "client_side": "required",
        "server_side": "required",
        "source_url": "https://github.com/ludwici/CopperDeco",
        "issues_url": "https://github.com/ludwici/CopperDeco/issues",
        "status": "draft",
        "requested_status": "approved",
        "initial_versions": [],
        "is_draft": True,
    }
    boundary = "----CopperDeco" + uuid.uuid4().hex
    icon = Path("src/main/resources/copperdeco.png").read_bytes()
    parts = [
        ("data", "application/json", json.dumps(payload).encode("utf-8"), None),
        ("icon", "image/png", icon, "copperdeco.png"),
    ]
    chunks = []
    for name, mime, content, filename in parts:
        chunks.append(("--" + boundary + "\r\n").encode())
        disposition = f'Content-Disposition: form-data; name="{name}"'
        if filename:
            disposition += f'; filename="{filename}"'
        chunks.append((disposition + "\r\n" + f"Content-Type: {mime}\r\n\r\n").encode())
        chunks.append(content)
        chunks.append(b"\r\n")
    chunks.append(("--" + boundary + "--\r\n").encode())
    result = api_json("POST", "/project", b"".join(chunks), "multipart/form-data; boundary=" + boundary)
    print(f"Created Modrinth project {result['id']} ({result.get('status')})")
    return result


def main():
    user = api_json("GET", "/user")
    account = user.get("username", "")
    if account.casefold() != "ludwici":
        raise RuntimeError(f"Modrinth token belongs to {account!r}, expected 'ludwici'")

    projects = api_json("GET", "/user/ludwici/projects")
    matches = [
        p for p in projects
        if p.get("title", "").casefold() == "copper deco"
        or p.get("source_url", "").rstrip("/").casefold() == "https://github.com/ludwici/copperdeco"
    ]
    if len(matches) > 1:
        raise RuntimeError("Multiple Modrinth projects matched Copper Deco; select the intended project manually")

    if matches:
        project = matches[0]
        print(f"Using existing Modrinth project {project['id']} ({project.get('slug')})")
    else:
        slugs = {p.get("slug", "").casefold() for p in projects}
        slug = "copper-deco"
        if slug in slugs:
            slug = "copper-deco-ludwici"
        project = create_project(slug)

    project_id = project["id"]
    with open(os.environ["GITHUB_OUTPUT"], "a", encoding="utf-8") as out:
        out.write(f"project_id={project_id}\n")


if __name__ == "__main__":
    try:
        main()
    except Exception as exc:
        print(str(exc), file=sys.stderr)
        sys.exit(1)
