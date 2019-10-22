#!/usr/bin/env sh

notinstalled() { printf "ERROR: %s not installed \n" "$*" >&2; }

docker -v &>/dev/null || {
    notinstalled docker
    ERROR=1
}

docker-compose -v &>/dev/null || {
    notinstalled docker-compose
    ERROR=1
}

if [[ ! -z $ERROR ]]; then
    exit 1
fi

git submodule update --init --recursive
docker-compose up -d

LOCAL_ADDR="http://localhost:8080"

printf "\nGraphqQL demo service is running: \n\n"
printf "  %-22s %s \n" \
    "Playground IDE:" "$LOCAL_ADDR/playground" \
    "GraphqiQL IDE:" "$LOCAL_ADDR/graphiql" \
    "Altair IDE :" "$LOCAL_ADDR/altair" \
    "Supscriptions App :" "$LOCAL_ADDR/"

printf "\n"
