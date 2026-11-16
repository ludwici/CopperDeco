import json
import os
import sys
import urllib.error
import urllib.request
import uuid
from pathlib import Path

API = "https://api.modrinth.com/v2"
TOKEN = os.environ["MODRINTH_TOKEN"]
USER_AGENT = "CopperDeco-release/1.0 (github.com/ludwici/CopperDeco)"


def api(path, method="GET", data=None, content_type="application/json"):
    headers = {"Authorization": TOKEN, "User-Agent": USER_AGENT}
    if data is not None:
        headers["Content-Type"] = content_type
    request = urllib.request.Request(API + path, headers=headers, data=data, method=method)
    try:
        with urllib.request.urlopen(request, timeout=60) as response:
            payload = response.read()
            return json.loads(payload) if payload else {}
    except urllib.error.HTTPError as error:
        details = error.read().decode("utf-8", errors="replace")
        raise RuntimeError(f"Modrinth API {method} {path}: HTTP {error.code}: {details[:1000]}") from error


def multipart(metadata, icon):
    boundary = "copperdeco-" + uuid.uuid4().hex
    payload = (
        f"--{boundary}\r\n"
        'Content-Disposition: form-data; name="data"\r\n'
        "Content-Type: application/json\r\n\r\n"
    ).encode() + json.dumps(metadata).encode() + b"\r\n"
    payload += (
        f"--{boundary}\r\n"
        'Content-Disposition: form-data; name="icon"; filename="copperdeco.png"\r\n'
        "Content-Type: image/png\r\n\r\n"
    ).encode() + icon.read_bytes() + b"\r\n"
    payload += f"--{boundary}--\r\n".encode()
    return payload, f"multipart/form-data; boundary={boundary}"


def main():
    if len(sys.argv) != 2 or sys.argv[1] not in ("ensure", "submit"):
        raise SystemExit("Usage: python3 ensure_modrinth_project.py ensure|submit")

    if sys.argv[1] == "submit":
        project_id = os.environ["MODRINTH_PROJECT_ID"]
        if os.environ.get("MODRINTH_PROJECT_CREATED") == "true":
            data = json.dumps({"requested_status": "approved"}).encode()
            api(f"/project/{project_id}", "PATCH", data)
            print(f"Submitted new Modrinth project {project_id} for review")
        else:
            print("Existing Modrinth project: no moderation submission needed")
        return

    projects = api("/user/ludwici/projects")
    project = next(
        (entry for entry in projects if (entry.get("slug") or "").lower() == "copperdeco"
         or entry.get("title", "").casefold() == "copper deco"),
        None,
    )
    created = False
    if project is None:
        metadata = {
            "slug": "copperdeco",
            "title": "Copper Deco",
            "description": "Decorative copper tiles for floors, walls and ceilings, with oxidation, waxing and scraping.",
            "body": (
                "# Copper Deco\n\n"
                "Adds 32 copper tile variants that can be placed on floors, walls and ceilings. "
                "Tiles support oxidation, waxing, axe scraping, lightning cleaning and waterlogging.\n\n"
                "## Minecraft 1.20.1 dependencies\n\n"
                "- Carpets Core 1.1.0 or newer\n"
                "- Vanilla Backport 1.2.2 or newer\n\n"
                "Available for Forge and Fabric. "
                "Source: https://github.com/ludwici/CopperDeco\n"
            ),
            "project_type": "mod",
            "categories": ["decoration"],
            "additional_categories": [],
            "client_side": "required",
            "server_side": "required",
            "license_id": "MIT",
            "source_url": "https://github.com/ludwici/CopperDeco",
            "issues_url": "https://github.com/ludwici/CopperDeco/issues",
            "is_draft": True,
            "initial_versions": [],
            "gallery_items": [],
        }
        data, content_type = multipart(metadata, Path("src/main/resources/copperdeco.png"))
        project = api("/project", "POST", data, content_type)
        created = True
        print("Created Modrinth project in draft status")
    else:
        print("Found existing Copper Deco project on the connected Modrinth account")
    project_id = project["id"]
    with open(os.environ["GITHUB_ENV"], "a", encoding="utf-8") as output:
        output.write(f"MODRINTH_PROJECT_ID={project_id}\n")
        output.write(f"MODRINTH_PROJECT_CREATED={str(created).lower()}\n")
    print(f"Modrinth project ID: {project_id}")


if __name__ == "__main__":
    main()
