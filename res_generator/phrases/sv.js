'use strict'

module.exports = [
	['POPUP_HEADER_PAYMENT', 'Betalning'],
	['POPUP_HEADER_SUBSEQUENT', 'Registrera dig'],
	['CARD_NUMBER', 'Kortnummer'],
	['CARD_EXPIRY', 'Utgångsdatum'],
	['CARD_CODE', 'CVC/CVV'],
	['PAY', 'Betala'],
	['PAYING', 'Betalar…'],
	['SAVE_CARD', 'Spara kort'],
	['SAVING', 'Sparar…'],
	['POWERED_BY', 'Säker betalning med Paylike'],
	['TDS_FULL_SCREEN_TEXT', 'Problem att se hela rutan?'],
	['TDS_FULL_SCREEN_LINK', 'Öppna i helskärmsläge.'],
	[
		'ERROR_TIMEOUT',
		'Oväntat problem\r\nKan det vara ett problem med din internetanslutning? Försök igen lite senare.',
	],
	[
		'ERROR_RATE_LIMIT',
		'Oväntat problem\r\nVi får just nu för många betalningsförfrågningar. Var god vänta några minuter innan du försöker igen.',
	],
	[
		'ERROR_UNEXPECTED',
		'Oväntat problem\nVar god försök igen lite senare eller kontakta vår support med meddelandet: {{message}}.',
	],
	[
		'ERROR_PUBLIC_KEY_IS_NOT_TEST',
		'Popupen för betalning är i testläge, men den public key som angavs är för ett livekonto. Var god använd en public key för ett testkonto.',
	],
	[
		'ERROR_PUBLIC_KEY_IS_TEST',
		'Popupen för betalning är i liveläge, men den public key som angavs är för ett testkonto. Var god använd en public key för ett livekonto.',
	],
	[
		'ERROR_PUBLIC_KEY_UNKNOWN',
		'Den public key som angavs är inte kopplad till ett Paylike konto. Uppdatera denna public key och försök igen.',
	],
	[
		'ERROR_PUBLIC_KEY_PAYMENTS_NOT_ALLOWED',
		'Denna public key får inte acceptera betalningar.',
	],
	[
		'ERROR_INVALID_CARD_NUMBER',
		'Ogiltigt kortnummer\nVänligen kontrollera kortnumret. Kontakta din bank om problemet kvarstår.',
	],
	[
		'ERROR_CARD_SCHEME_UNKNOWN',
		'Ogiltigt kortnummer eller stöd saknas\nKortnumret är felaktigt eller så stöds inte kortmärket. Kort som stöds: {{supported}}.',
	],
	[
		'ERROR_CARD_SCHEME_UNSUPPORTED',
		'{{scheme}}-kort stöds inte\n{{scheme}}-kort stöds tyvärr inte. Använd något av följande: {{supported}}.',
	],
	[
		'ERROR_CARD_SECURITY_CODE_INVALID',
		'Ogiltig CVC/CVV (säkerhetskod)\nKontrollera det CVC/CVV (säkerhetskod) som finns på baksidan av ditt kort. Kontakta din bank om problemet kvarstår.',
	],
	[
		'ERROR_CARD_EXPIRED',
		'Kortet har gått ut\nDitt kort är utgånget. Vänligen försök med ett annat kort.',
	],
	[
		'ERROR_CARD_DISABLED',
		'Kortet är spärrat\nDetta kort är spärrat, var god kontakta din bank för mer information.',
	],
	[
		'ERROR_CARD_LOST',
		'Kort spärrat\nKortet har markerats som borttappat eller stulet, kontakta din bank för mer information.',
	],
	[
		'ERROR_AMOUNT_LIMIT',
		'Beloppsgräns överskriden\nBeloppsgränsen på ditt kort är nådd. Kontakta din bank för att nollställa eller höja gränsen.',
	],
	[
		'ERROR_INSUFFICIENT_FUNDS',
		'Otillräckliga medel\r\nDet finns inte tillräckligt saldo på kortet för att täcka köpet. Var god försök med ett annat kort eller lägg till pengar på ditt konto. Kontakta din bank om problemet kvarstår.',
	],
	[
		'ERROR_RECEIVER_BLOCKED',
		'Handlare blockerad\nBetalningen avvisades av din bank på grund av att handlaren blockerats. Vänligen Kontakta din bank.',
	],
	[
		'ERROR_REJECTED_BY_ISSUER',
		'Kortet nekades\nKortet blev nekat av banken, som har utställt det. Vänligen kontakta din bank.',
	],
	[
		'ERROR_REJECTED',
		'Betalningen nekades\nVar god försök igen om en liten stund eller kontakta vår support om problemet kvarstår.',
	],
	[
		'ERROR_TDSECURE_REQUIRED',
		'3-D Secure krävs\r\n3-D Secure (Visa Secure/Mastercard Identity Check) krävdes för att acceptera denna betalning, men det var inte möjligt att erhålla. Var god försök igen om en liten stund eller kontakta din bank.',
	],
	[
		'ERROR_TDSECURE_FAILED',
		'3-D Secure misslyckades\n3-D Secure (Visa Secure/Mastercard Identity Check) krävs men lyckades inte erhålla autentisering. Var god försök igen eller kontakta din bank.',
	],
]
