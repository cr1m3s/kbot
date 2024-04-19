APP=$(shell basename $(shell git remote get-url origin))
# REGISTRY=cr1m3s
VERSION=$(shell git describe --tags --abbrev=0)-$(shell git rev-parse --short HEAD)
TARGETOS=linux
TARGETARCH=amd64
DOCKER_TAG=${REGISTRY}/${APP}:${VERSION}-${TARGETOS}-${TARGETARCH}

format:
	gofmt -s -w ./

lint:
	golint

test:
	go test -v

goget:
	go get

build: format goget
	CGO_ENABLED=0 GOOS=${TARGETOS} GOARCH=${TARGETARCH} go build -v -o kbot --ldflags "-X="github.com/cr1m3s/kbot/cmd.appVersion=${VERSION}

image:
	docker build . -t ${DOCKER_TAG}

push:
	docker push ${DOCKER_TAG}

run:
	docker run ${DOCKER_TAG}

clean:
	rm -rf kbot
