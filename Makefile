.PHONY: build build-nocache test tag-latest push push-latest release git-tag-version

package:
	helm package charts/$(chart) -d docs/
	helm repo index ./docs --url https://tomxiaoyz.github.io/dep