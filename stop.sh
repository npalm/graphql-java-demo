#!/usr/bin/env sh

notinstalled() { printf "ERROR: %s not installed \n" "$*" >&2; }

docker -v &>/dev/null || {
    notinstalled docker
    ERROR=1
}

docker-compose -v &>/dev/null || {
    notinstalled docker
    ERROR=1
}

if [[ ! -z $ERROR ]]; then
    exit 1
fi

docker-compose rm -f -v -s
