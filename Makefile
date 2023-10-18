.PHONY: build build-nocache test tag-latest push push-latest release git-tag-version

package:
	helm package dep/$(chart)
	helm repo index --url https://tomxiaoyz.github.io/big-data-platform-public/ --merge index.yaml .