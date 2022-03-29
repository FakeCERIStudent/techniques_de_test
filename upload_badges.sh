checkstyle=$(cat $1 | sed 's/\"/\\\"/g')
javadoc=$(cat $2 | sed 's/\"/\\\"/g')
dependency=$(cat $3 | sed 's/\"/\\\"/g')

curl \
-X PATCH \
-H "Accept: application/vnd.github.v3+json" \
  https://api.github.com/gists/c4a382759d0291e9906f87fb85b1d3f8 \
-u FakeCERIStudent:$GitHubAccessToken \
-d '{"files":{"checkstyle-result.svg":{"content":"'"$checkstyle"'"}, "javadoc_coverage.svg": {"content":"'"$javadoc"'"}, "dependency_check.svg":{"content":"'"$dependency"'"}}}'
