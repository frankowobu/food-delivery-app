#!/usr/bin/env python3
import os
import re
import sys
import semver
import subprocess


def git(*args):
    return subprocess.check_output(["git"] + list(args))


def tag_repo(tag):
    url = os.environ["CI_REPOSITORY_URL"]

    # Transforms the repository URL to the SSH URL
    push_url = re.sub(r'.+@([^/]+)/', r'git@\1:', url)

    git("remote", "set-url", "--push", "origin", push_url)
    git("tag", tag)
    git("push", "origin", tag)


def bump(latest, commit_messages):
    major_bump = any('BREAKING CHANGE' in msg for msg in commit_messages)
    minor_bump = any('feat' in msg for msg in commit_messages)

    if major_bump:
        return semver.bump_major(latest)
    elif minor_bump:
        return semver.bump_minor(latest)
    else:
        return semver.bump_patch(latest)


def get_commit_messages():
    try:
        commits = git("log", "--oneline", "-n", "10").decode().strip().split('\n')
        messages = [commit.split(' ', 1)[1] for commit in commits]
        return messages
    except subprocess.CalledProcessError:
        return []


def main():
    try:
        latest = git("describe", "--tags").decode().strip()
    except subprocess.CalledProcessError:
        version = "1.0.0"
    else:
        if '-' not in latest:
            print(latest)
            with open("version.txt", "w") as f:
                f.write(latest)
            return 0

        commit_messages = get_commit_messages()
        version = bump(latest, commit_messages)

    tag_repo(version)
    with open("version.txt", "w") as f:
        f.write(version)
    print(version)

    return 0


if __name__ == "__main__":
    sys.exit(main())
