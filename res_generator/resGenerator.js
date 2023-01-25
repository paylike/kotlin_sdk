const fs = require('fs');
const path = require('path');
const phrases = require('./phrases/index.js');
// now we have 19 languages to support at 2022.10.24.

// LanguageCode e.g.: "-es"
const generationPath = (languageCode) => `./../kotlin_sdk/src/main/res/values-${languageCode}`; 
var fileName = "strings.xml"

function generateLocale(code, strings) {
    
// Iterating over the input array
var starterString = "<resources>\n"
var finisherString = "</resources>\n"
var iteratingResult = "" + starterString

for (var i = 0; i< strings.length; i++) {
    var replacedNewLine = strings[i][1].replace(/\n/g, "\\n");
    var replacedSingleQuotes = "";
    replacedSingleQuotes = replacedNewLine.replace(/'/gu, "\\'");

    // var regExp = new RegExp('{{\w+}}', '');
    var regExp = /{{\w+}}/;

    var index = 0;
    const foundWordsMap = [];
    while (replacedSingleQuotes.match(regExp) != null) {
        // save found word
        var foundWord = replacedSingleQuotes.match(regExp)[0];
        var weHaveIt = false;
        var previousEntry = {};
        // we check if we have it already
        foundWordsMap.forEach(element => {
            if (foundWord == element[1]) {
                weHaveIt = true;
                previousEntry = element;
            }
        });
        // save it to list if we dont, then execute the replacement
        if (!weHaveIt) {
            foundWordsMap.push([index, foundWord]);
            replacedSingleQuotes = replacedSingleQuotes.replace(regExp, `\%${index + 1}\$s`);
            index++;
        } else { // else we dont save it, but replace with the previous entry index
            replacedSingleQuotes = replacedSingleQuotes.replace(regExp, `\%${previousEntry[0] + 1}\$s`);
        }
    }
    var replacedEmbededInterpolations = replacedSingleQuotes;
    // generating the corresponding line
    iteratingResult += `    <string name="${strings[i][0]}">${replacedEmbededInterpolations}</string>\n`
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
