'use strict'

module.exports = [
	['POPUP_HEADER_PAYMENT', 'Platba'],
	['POPUP_HEADER_SUBSEQUENT', 'Registrovať'],
	['CARD_NUMBER', 'Číslo karty'],
	['CARD_EXPIRY', 'Platnosť mesiac/rok'],
	['CARD_CODE', 'CVC'],
	['PAY', 'Zaplatiť'],
	['PAYING', 'Platba prebieha…'],
	['SAVE_CARD', 'Uložiť kartu'],
	['SAVING', 'Ukladanie…'],
	['POWERED_BY', 'Bezpečná platba od Paylike'],
	['TDS_FULL_SCREEN_TEXT', 'Máte problém a nevidíte toto okno?'],
	['TDS_FULL_SCREEN_LINK', 'Otvoriť na celú obrazovku.'],
	[
		'ERROR_TIMEOUT',
		'Neočakávaná chyba\nMôže to byť problém s internetovým pripojením? Prosím skúste znovu to o niečo neskôr.',
	],
	[
		'ERROR_RATE_LIMIT',
		'Neočakávaná chyba\nMomentálne prijímame príliš veľa žiadostí platbu. Pred opakovaním platby počkajte niekoľko minút.',
	],
	[
		'ERROR_UNEXPECTED',
		'Neočakávaná chyba\nProsím skúste to o niečo neskôr, alebo kontaktujte našu podporu so správou: {{message}}',
	],
	[
		'ERROR_PUBLIC_KEY_IS_NOT_TEST',
		'Platobné okno je v testovacom móde, ale použitý verejný kľúč patrí k produkčnému účtu. Prosím použite verejný kľúč patriaci k testovaciemu účtu.',
	],
	[
		'ERROR_PUBLIC_KEY_IS_TEST',
		'Platobné okno je v produkčnom móde, ale použitý verejný kľúč patrí k testovaciemu účtu. Prosím použite verejný kľúč patriaci k produkčnému účtu.',
	],
	[
		'ERROR_PUBLIC_KEY_UNKNOWN',
		'Zadaný verejný kľúč nepatrí k Paylike účtu. Zadajte správny verejný kľúč a skúste znova.',
	],
	[
		'ERROR_PUBLIC_KEY_PAYMENTS_NOT_ALLOWED',
		'Platby s verejným kľúčom nie su povolené.',
	],
	[
		'ERROR_INVALID_CARD_NUMBER',
		'Neplatné číslo karty\nProsím, skontrolujte číslo karty. Ak problém pretrváva, kontaktujte prosím svoju banku.',
	],
	[
		'ERROR_CARD_SCHEME_UNKNOWN',
		'Neplatné číslo karty alebo nepodporovaná karta\nČíslo karty je nesprávne, alebo druh karty nie je podporovaný. Použite prosím: {{supported}}.',
	],
	[
		'ERROR_CARD_SCHEME_UNSUPPORTED',
		'{{scheme}} karty nie sú podporované\n{{scheme}} karty žiaľ nie sú podporované. Použite prosím: {{supported}}.',
	],
	[
		'ERROR_CARD_SECURITY_CODE_INVALID',
		'Neplatný CVC (bezpečnostný kód)\nProsím, skontrolujte si svoj CVC (bezpečnostný kód), zvyčajne ho nájdete na zadnej strane Vašej karty. Ak problém pretrváva, kontaktujte prosím svoju banku.',
	],
	[
		'ERROR_CARD_EXPIRED',
		'Vypršala platnosť karty\nVypršala platnosť Vašej karty. Prosím, vyskúšajte inú kartu.',
	],
	[
		'ERROR_CARD_DISABLED',
		'Karta je zablokovaná\nTáto karta je zablokovaná, kontaktujte prosím svoju banku pre viac informácií.',
	],
	[
		'ERROR_CARD_LOST',
		'Karta bola stratená alebo ukradnutá\nKarta bola zablokovaná a označená ako stratená alebo ukradnutá, kontaktujte prosím svoju banku pre viac informácií.',
	],
	[
		'ERROR_AMOUNT_LIMIT',
		'Prekročenie limitu na karte\nLimit na karte bol dosiahnutý. Kontaktujte prosím svoju banku pre obnovenie alebo zvýšenie limitu.',
	],
	[
		'ERROR_INSUFFICIENT_FUNDS',
		'Nedostatok finančných prostriedkov\nNa karte nie je dostatok finančných prostriedkov na uhradenie nákupu. Prosím, skúste inú kartu, alebo doplňte finančné prostriedky na Vašu kartu. Ak problém pretrváva, kontaktujte prosím svoju banku.',
	],
	[
		'ERROR_RECEIVER_BLOCKED',
		'Blokovaný obchodník\nPlatba bola zamietnutá Vašou bankou z dôvodu zablokovania obchodníka. Prosím, kontaktujte Vašu banku.',
	],
	[
		'ERROR_REJECTED_BY_ISSUER',
		'Karta bola odmietnutá\nKarta bola odmietnutá Vašou bankou. Prosím, kontaktujte svoju banku.',
	],
	[
		'ERROR_REJECTED',
		'Platba bola odmietnutá\nProsím skúste vykonať platbu o niečo neskôr, alebo kontaktujte našu podporu ak problém pretrváva.',
	],
	[
		'ERROR_TDSECURE_REQUIRED',
		'3-D Secure overenie\n3-D Secure (Visa Secure/Mastercard Identity Check) overenie je nutné k akceptovaniu tejto platby, ale nebolo možné ho získať. Skúste to znova o niečo neskôr, alebo kontaktujte Vašu banku.',
	],
	[
		'ERROR_TDSECURE_FAILED',
		'3-D Secure overenie zlyhalo\n3-D Secure (Visa Secure/Mastercard Identity Check) overenie je nutné ale bolo neúspešné. Skúste to znova, alebo kontaktujte Vašu banku.',
	],
]
