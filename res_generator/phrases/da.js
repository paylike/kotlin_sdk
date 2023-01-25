'use strict'

module.exports = [
	['POPUP_HEADER_PAYMENT', 'Betaling'],
	['POPUP_HEADER_SUBSEQUENT', 'Tilmeld'],
	['CARD_NUMBER', 'Kortnummer'],
	['CARD_EXPIRY', 'Udløbsdato'],
	['CARD_CODE', 'Kontrolcifre'],
	['PAY', 'Betal'],
	['PAYING', 'Betaler...'],
	['SAVE_CARD', 'Gem kort'],
	['SAVING', 'Gemmer...'],
	['POWERED_BY', 'Sikker betaling med Paylike'],
	['TDS_FULL_SCREEN_TEXT', 'Problemer med at se hele vinduet?'],
	['TDS_FULL_SCREEN_LINK', 'Åben i fuld skærm.'],
	[
		'ERROR_TIMEOUT',
		'Betalingen kunne ikke gennemføres\nDer var et problem med forbindelsen til internettet. Vent venligst ét minut og prøv igen.',
	],
	[
		'ERROR_RATE_LIMIT',
		'Betalingen kunne ikke gennemføres\nVi modtager i øjeblikket for mange forespørgsler. Vent venligst to minutter og prøv igen.',
	],
	[
		'ERROR_UNEXPECTED',
		'Betalingen kunne ikke gennemføres\nDer skete en uventet fejl. Prøv venligst igen lidt senere eller kontakt vores kundeservice med følgende besked: {{message}}.',
	],
	[
		'ERROR_PUBLIC_KEY_IS_NOT_TEST',
		"Betalingspopuppen er i test mode, men public key'en tilhører en live-konto. Benyt i stedet en public key til en testkonto.",
	],
	[
		'ERROR_PUBLIC_KEY_IS_TEST',
		"Betalingspopuppen er i live mode, men public key'en tilhører en testkonto. Benyt i stedet en public key til en live-konto.",
	],
	[
		'ERROR_PUBLIC_KEY_UNKNOWN',
		"Public key'en der bruges tilhører ikke en Paylike konto. Opdater public key'en og prøv igen.",
	],
	[
		'ERROR_PUBLIC_KEY_PAYMENTS_NOT_ALLOWED',
		"Public key'en er ikke godkendt til at tage imod betalinger.",
	],
	[
		'ERROR_INVALID_CARD_NUMBER',
		'Ugyldigt kortnummer\nKontrollér venligst kortnummeret, og kontakt din bank, hvis problemet fortsætter.',
	],
	[
		'ERROR_CARD_SCHEME_UNKNOWN',
		'Ugyldigt kortnummer eller kort-type\nKontrollér venligst kortnummeret samt at dit kort-type er en af følgende: {{supported}}',
	],
	[
		'ERROR_CARD_SCHEME_UNSUPPORTED',
		'{{scheme}} kort er ikke understøttet\n{{scheme}} kort er desværre ikke understøttet. Brug venligst et: {{supported}}.',
	],
	[
		'ERROR_CARD_SECURITY_CODE_INVALID',
		'Ugyldige kontrolcifre\nKontrollér kontrolcifrene (sikkerhedskode), som findes på bagsiden af dit kort. Kontakt din bank, hvis problemet fortsætter.',
	],
	[
		'ERROR_CARD_EXPIRED',
		'Kortet er udløbet\nDit kort er udløbet. Prøv venligst med et andet kort.',
	],
	[
		'ERROR_CARD_DISABLED',
		'Kortet er spærret\nKontakt din bank for nærmere information om årsagen.',
	],
	[
		'ERROR_CARD_LOST',
		'Kortet er spærret\nDit kort er markeret som tabt eller stjålet. Kontakt din bank for nærmere information om årsagen.',
	],
	[
		'ERROR_AMOUNT_LIMIT',
		'Beløbsgrænse overskredet\nBeløbsgrænsen på dit kort er nået. Kontakt din bank for at nulstille eller hæve grænsen.',
	],
	[
		'ERROR_INSUFFICIENT_FUNDS',
		'Manglende dækning på kortet\nDer er ikke penge nok på kortet til at gennemføre købet. Prøv venligst med et andet kort.',
	],
	[
		'ERROR_RECEIVER_BLOCKED',
		'Betalingen blev afvist af din bank\nDin bank har blokeret butikken. Kontakt din bank for at fjerne blokeringen.',
	],
	[
		'ERROR_REJECTED_BY_ISSUER',
		'Kortet blev afvist\nKortet blev afvist af banken, som har udstedt det. Kontakt venligst din bank.',
	],
	[
		'ERROR_REJECTED',
		'Betalingen kunne ikke gennemføres\nPrøv venligst igen lidt senere eller kontakt vores kundeservice, hvis betalingen fejler gentagende.',
	],
	[
		'ERROR_TDSECURE_REQUIRED',
		'3-D Secure påkrævet\nBetalingen kunne ikke verificeres med 3-D Secure (Visa Secure/Mastercard Identity Check). Prøv igen senere eller kontakt din bank.',
	],
	[
		'ERROR_TDSECURE_FAILED',
		'3-D Secure kunne ikke gennemføres\nBetalingen kunne ikke verificeres med 3-D Secure (Visa Secure/Mastercard Identity Check). Prøv igen senere eller kontakt din bank.',
	],
]
