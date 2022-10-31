package com.github.paylike.kotlin_sdk

import android.os.LocaleList

enum class SupportedLocaleEnum(val language: String) {
    bg("BULGARIAN"),
    cs("CZECH"),
    da("DANISH"),
    nl("DUTCH"),
    en("ENGLISH"),
    et("ESTONIAN"),
    fi("FINNISH"),
    fr("FRENCH"),
    de("GERMAN"),
    el("GREEK"), // TODO "gr" on paylike system
    kl("GREENLANDIC"),
    hu("HUNGARIAN"),
    no("NORWEGIAN"),
    pl("POLISH"),
    ro("ROMANIAN"),
    sk("SLOVAK"),
    sl("SLOVENIAN"), // TODO "si" on paylike system
    es("SPANISH"),
    sv("SWEDISH"),
}

val supportedLocaleEnumList: List<SupportedLocaleEnum> = SupportedLocaleEnum.values().toList()

val supportedLocaleList: LocaleList =
    LocaleList.forLanguageTags(
        SupportedLocaleEnum.values().joinToString(separator = ",") {
            supportedLanguages: SupportedLocaleEnum ->
            supportedLanguages.toString()
        }
    )
