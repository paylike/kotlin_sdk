# !/bin/zsh

## delete previous versions from ../kotlin_sdk/src/main/res/
# rm ./../...

## download source JSONs to this (res_generator) folder
#curl https://sadf.asdf.fdsa.asdf.js > ./...

## create the generated files to ../kotlin_sdk/src/main/res/
# touch ./../...
array=('bg' 'cs' 'da' 'de' 'en' 'es' 'et' 'fi' 'fr' 'gr' 'hu' 'kl' 'nl' 'no' 'pl' 'ro' 'si' 'sk' 'sv')
for i in {1..$#array}; do mkdir "./../kotlin_sdk/src/main/res/values-$array[$i]"; done
for i in {1..$#array}; do touch "./../kotlin_sdk/src/main/res/values-$array[$i]/strings.xml"; done

# /Users/laszlokocsis/werk/projektek/paylike/android/kotlin_sdk/kotlin_sdk/src/main/res
#     mkdir /../kotlin_sdk/src/main/res/values-bg

## generate the content of the generated res xml files
# node ./resGenerator.js

## remove source JSONs from this (res_generator) folder
# rm ./...







##!/bin/zsh
#
### delete previous verions
#rm ./kotlin_currencies/src/main/java/com/github/paylike/kotlin_currencies/generated/CurrencyCode.kt
#rm ./kotlin_currencies/src/main/java/com/github/paylike/kotlin_currencies/generated/PaylikeCurrencyCollection.kt
#
### download source json
#curl https://raw.githubusercontent.com/paylike/currencies/master/currencies.json > ./kotlin_currencies/src/main/java/com/github/paylike/kotlin_currencies/generated/currencies.json
#
### create the generated files
#touch ./kotlin_currencies/src/main/java/com/github/paylike/kotlin_currencies/generated/CurrencyCode.kt
#touch ./kotlin_currencies/src/main/java/com/github/paylike/kotlin_currencies/generated/PaylikeCurrencyCollection.kt
#
### generate the content of the generated kotlin files
#node ./tools/builder.js
#
### remove source json
#rm ./kotlin_currencies/src/main/java/com/github/paylike/kotlin_currencies/generated/currencies.json
