const fs = require('fs');
const path = require('path');
const phrases = require('./phrases/index.js');
// now we have 19 languages to support at 2022.10.24.

// LanguageCode e.g.: "-es-rES"
const generationPath = (languageCode) => `./../kotlin_sdk/src/main/res/values-${languageCode}`; 
var fileName = "strings.xml"

function generateLocale(code, strings) {
    
// Iterating over the input array
var starterString = "<resources>\n"
var finisherString = "</resources>\n"
var iteratingResult = "" + starterString

for (var i = 0; i< strings.length; i++) {
    var replacedNewLine = strings[i][1].replace(/\n/g, "\\n");
    var replacedsingleQuotes = replacedNewLine.replace(/'/gu, "\\'");
    var replacedInterpolationStarter = replacedsingleQuotes.replace(/{{/g, "${");
    var replacedInterpolationEnder = replacedInterpolationStarter.replace(/}}/g, "}");
    iteratingResult += `    <string name="${strings[i][0]}">${replacedInterpolationEnder}</string>\n`
}
iteratingResult += finisherString
// Printing to file
var file = path.join(__dirname, generationPath(code), fileName);
fs.writeFileSync(file, iteratingResult);
// console.log(iteratingResult);
}

for (let key in phrases) {
    generateLocale(key, phrases[key]);
}
