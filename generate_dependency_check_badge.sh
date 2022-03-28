nb_vulnerable_dependencies=$(cat $1 | grep -oP "Vulnerable Dependencies\<\/i\>\:\&nbsp\;\<span id=\"vulnerableCount\"\>\K[^\<]*")
nb_vulnerabilities=$(cat $1 | grep -oP "Vulnerable Dependencies\<\/i\>\:\&nbsp\;\<span id=\"vulnerableCount\"\>\K[^\<]*")

nb_missing=$((nb_vulnerable_dependencies + nb_vulnerabilities))

color="red"
if [ "$nb_missing" -eq "0" ]; then
	color="green"
fi

url="https://img.shields.io/badge/Dependency%20Check-$nb_vulnerable_dependencies%20Vulnerable%20dependencies%20%7C%20$nb_vulnerabilities%20Vulnerabilities-$color"

wget -O $2 $url
