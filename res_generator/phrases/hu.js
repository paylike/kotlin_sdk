'use strict'

module.exports = [
	['POPUP_HEADER_PAYMENT', 'Fizetés'],
	['POPUP_HEADER_SUBSEQUENT', 'Regisztráció'],
	['CARD_NUMBER', 'Kártyaszám'],
	['CARD_EXPIRY', 'Lejárat hónap/év'],
	['CARD_CODE', 'Biztonsági kód'],
	['PAY', 'Fizetés'],
	['PAYING', 'Fizetés…'],
	['SAVE_CARD', 'Kártya mentése'],
	['SAVING', 'Mentés...'],
	['POWERED_BY', 'Biztonságos fizetés - Paylike'],
	['TDS_FULL_SCREEN_TEXT', 'Megjelenítési probléma?'],
	['TDS_FULL_SCREEN_LINK', 'Megnyitás teljes képernyőn.'],
	[
		'ERROR_TIMEOUT',
		'Váratlan hiba\nLehetséges, hogy probléma lépett fel az internetkapcsolattal. Kérjük próbálja újra később.',
	],
	[
		'ERROR_RATE_LIMIT',
		'Váratlan hiba\nJelenleg az átlagnál jelentősen több tranzakció kerül feldolgozásra. Kérjük várjon néhány percet és próbálja újra.',
	],
	[
		'ERROR_UNEXPECTED',
		'Váratlan hiba\nKérjük próbálja újra ismét vagy lépjen velünk kapcsolatba az alábbi üzenettel: {{message}}.',
	],
	[
		'ERROR_PUBLIC_KEY_IS_NOT_TEST',
		'A fizetési ablak teszt módban üzemel de a megadott public key egy élő számlához tartozik. Kérjük használja egy teszt számla public key azonosítóját.',
	],
	[
		'ERROR_PUBLIC_KEY_IS_TEST',
		'A fizetési ablak élő módban üzemel de a megadott public key egy teszt számlához tartozik. Kérjük használja egy élő számla public key azonosítóját.',
	],
	[
		'ERROR_PUBLIC_KEY_UNKNOWN',
		'A megadott public key azonosítóhoz nem tartozik Paylike számla. Frissítse a public key mezőt és próbálja újra.',
	],
	[
		'ERROR_PUBLIC_KEY_PAYMENTS_NOT_ALLOWED',
		'A public key azonosítóhoz tartozó számla nincs engedélyezve tranzakciók fogadásához.',
	],
	[
		'ERROR_INVALID_CARD_NUMBER',
		'Érvénytelen kártyaszám\nKérjük ellenőrizze kártyája adatait. Ha a probléma továbbra is fennáll, kérjük lépjen kapcsolatba bankjával.',
	],
	[
		'ERROR_CARD_SCHEME_UNKNOWN',
		'Érvénytelen kártyaszám vagy a kártya nem támogatott\nHibás kártyaszám vagy a kártya nem támogatott. Támogatott kártyák: {{supported}}.',
	],
	[
		'ERROR_CARD_SCHEME_UNSUPPORTED',
		'Nem támogatott kártya: {{scheme}} \nSajnos {{scheme}} által kiállított kártyákat jelenleg nem fogadunk el. Kérjük használja az itt látható szolgáltatók egyikét: {{supported}}.',
	],
	[
		'ERROR_CARD_SECURITY_CODE_INVALID',
		'Hibás biztonsági kód (CVC)\nKérjük ellenőrizze biztonsági kódját (CVC), ami általában kártyája hátoldalán található. Ha a probléma továbbra is fennáll, kérjük lépjen kapcsolatba bankjával.',
	],
	[
		'ERROR_CARD_EXPIRED',
		'A kártya lejárt\nKártyája lejárt. Kérjük próbáljon meg egy másikat.',
	],
	[
		'ERROR_CARD_DISABLED',
		'A kártya korlátozva van\nA kártya korlátozva van, kérjük lépjen kapcsolatba bankjával további információért.',
	],
	[
		'ERROR_CARD_LOST',
		'Elveszett vagy ellopott kártya\nA kártyát zárolták és elveszettnek vagy lopottnak nyilvánították, lépjen kapcsolatba bankjával további információért.',
	],
	[
		'ERROR_AMOUNT_LIMIT',
		'Kártyája túllépte az előírt keretet\nKártyája elérte az összeghatárt. Kérjük lépjen kapcsolatba bankjával, hogy törölje vagy növelje keretét.',
	],
	[
		'ERROR_INSUFFICIENT_FUNDS',
		'Fedezethiány\nNem áll rendelkezésre elegendő fedezet a vásárlás jóváhagyásához. Kérjük próbáljon meg egy másik kártyát vagy növelje fedezetét. Ha a probléma továbbra is fennáll, kérjük lépjen kapcsolatba bankjával.',
	],
	[
		'ERROR_RECEIVER_BLOCKED',
		'Kereskedő letiltva\nA fizetés elutasításra került a bank által mivel a kereskedő letiltásra került. Kérjük lépjen kapcsolatba a bankjával.',
	],
	[
		'ERROR_REJECTED_BY_ISSUER',
		'A kártyát elutasították\nA bank elutasított a kártyát. Kérjük lépjen kapcsolatba bankjával.',
	],
	[
		'ERROR_REJECTED',
		'A fizetést elutasították\nKérjük próbálja újra ismét vagy lépjen velünk kapcsolatba ha a probléma ismét jelentkezik.',
	],
	[
		'ERROR_TDSECURE_REQUIRED',
		'3-D Secure szükséges\n3-D Secure (Visa Secure/Mastercard Identity Check) szükséges a tranzakció elvégzéséhez azonban a folyamat végrehajtása nem volt lehetséges. Kérjük próbálja újra vagy lépjen kapcsolatba bankjával.',
	],
	[
		'ERROR_TDSECURE_FAILED',
		'Sikertelen 3-D Secure\n3-D Secure (Visa Secure/Mastercard Identity Check) szükséges a tranzakció elvégzéséhez azonban a folyamat végrehajtása nem volt lehetséges. Kérjük próbálja újra vagy lépjen kapcsolatba bankjával.',
	],
]
