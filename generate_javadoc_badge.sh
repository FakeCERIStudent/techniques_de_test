nb_methods=$(cat $1 | grep "MissingJavadocMethod" | wc -l)
nb_packages=$(cat $1 | grep "MissingJavadocPackage" | wc -l)
nb_types=$(cat $1 | grep "MissingJavadocType" | wc -l)

nb_missing=$((nb_methods + nb_packages + nb_types))

color="red"
if [ "$nb_missing" -eq "0" ]; then
	color="green"
fi

url="https://img.shields.io/badge/missing%20javadoc-$nb_methods%20methods%20%7C%20$nb_packages%20packages%20%7C%200%20types-$color"

wget -O $2 $url
