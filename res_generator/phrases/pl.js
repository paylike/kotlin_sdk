'use strict'

module.exports = [
	['POPUP_HEADER_PAYMENT', 'Płatność'],
	['POPUP_HEADER_SUBSEQUENT', 'Zapisz się'],
	['CARD_NUMBER', 'Numer karty'],
	['CARD_EXPIRY', 'Miesiąc/rok ważności'],
	['CARD_CODE', 'Kod CVC'],
	['PAY', 'Zapłać'],
	['PAYING', 'Płacenie…'],
	['SAVE_CARD', 'Zapisz kartę'],
	['SAVING', 'Zapisywanie…'],
	['POWERED_BY', 'Bezpieczna płatność przez Paylike'],
	['TDS_FULL_SCREEN_TEXT', 'Problem z wyświetleniem tego okna?'],
	['TDS_FULL_SCREEN_LINK', 'Otwórz na pełnym ekranie.'],
	[
		'ERROR_TIMEOUT',
		'Niespodziewany błąd\nPrawdopodobnie masz problemy z połączeniem internetowym. Spróbuj ponownie za jakiś czas.',
	],
	[
		'ERROR_RATE_LIMIT',
		'Niespodziewany błąd\nObsługujemy obecnie zbyt dużą ilość płatności. Spróbuj ponownie za kilka minut.',
	],
	[
		'ERROR_UNEXPECTED',
		'Niespodziewany problem\nSpróbuj ponownie za chwilę lub napisz wiadomość do obsługi: {{message}}.',
	],
	[
		'ERROR_PUBLIC_KEY_IS_NOT_TEST',
		'Okno płatności jest w trybie „test”, ale podano klucz „public key” dla konta „live account”. Użyj klucza „public key” dla konta „test account”.',
	],
	[
		'ERROR_PUBLIC_KEY_IS_TEST',
		'Okno płatności jest w trybie „live”, ale podano klucz „public key” dla konta „test account”. Użyj klucza „public key” dla konta „live account”.',
	],
	[
		'ERROR_PUBLIC_KEY_UNKNOWN',
		'Podany klucz „public key” nie jest powiązany z kontem Paylike account”. Uzupełnij klucz „public key” i spróbuj ponownie.',
	],
	[
		'ERROR_PUBLIC_KEY_PAYMENTS_NOT_ALLOWED',
		'Ten klucz „public key” jest niedozwolony w celu przyjmowania płatności.',
	],
	[
		'ERROR_INVALID_CARD_NUMBER',
		'Nieprawidłowy numer karty\nProsimy sprawdzić wszystkie dane karty. Jeśli problem będzie się powtarzał, skontaktuj się ze swoim bankiem.',
	],
	[
		'ERROR_CARD_SCHEME_UNKNOWN',
		'Nieprawidłowy numer karty lub karta nie jest obsługiwana\nNumer karty jest nieprawidłowy lub typ karty nie jest obsługiwany.',
	],
	[
		'ERROR_CARD_SCHEME_UNSUPPORTED',
		'Karty {{scheme}} nie są obsługiwane\nNiestety, ale karty {{scheme}} nie są obsługiwane. Użyj jednej z następujących kart: {{supported}}.',
	],
	[
		'ERROR_CARD_SECURITY_CODE_INVALID',
		'Nieprawidłowe CVC (kod zabezpieczający)\nProsimy sprawdzić CVC (kod zabezpieczający), zazwyczaj znajduje się on na odwrocie karty. Jeśli problem będzie się powtarzał, skontaktuj się ze swoim bankiem.',
	],
	[
		'ERROR_CARD_EXPIRED',
		'Karta straciła ważność\nTwoja karta straciła ważność. Prosimy spróbować z inną kartą.',
	],
	[
		'ERROR_CARD_DISABLED',
		'Karta została zastrzeżona\nTa karta została zastrzeżona. Skontaktuj się ze swoim bankiem, aby uzyskać dodatkowe informacje.',
	],
	[
		'ERROR_CARD_LOST',
		'Skradziona lub zgubiona karta\nTa karta została zablokowana i oznaczona jako zgubiona lub skradziona. Prosimy skontaktować się ze swoim bankiem, by uzyskać więcej informacji.',
	],
	[
		'ERROR_AMOUNT_LIMIT',
		'Przekroczono limit kwoty karty\nOsiągnięto limit kwoty na karcie. Prosimy skontaktować się ze swoim bankiem, aby wyzerować lub zwiększyć limit.',
	],
	[
		'ERROR_INSUFFICIENT_FUNDS',
		'Niewystarczające środki\nBrak wystarczających środków na pokrycie kosztów zakupu. Spróbuj użyć innej karty lub przelej środki na swoje konto. Jeśli problem będzie się powtarzał, skontaktuj się ze swoim bankiem.',
	],
	[
		'ERROR_RECEIVER_BLOCKED',
		'Sprzedawca zablokowany\nPłatność została odrzucona przez bank z powodu zablokowania sprzedawcy. Skontaktuj się ze swoim bankiem',
	],
	[
		'ERROR_REJECTED_BY_ISSUER',
		'Karta została odrzucona\nKarta została odrzucona przez Twój bank. Skontaktuj się ze swoim bankiem.',
	],
	[
		'ERROR_REJECTED',
		'Płatność została odrzucona\nSpróbuj ponownie za chwilę lub skontaktuj się z obsługą, jeśli problem będzie się powtarzał.',
	],
	[
		'ERROR_TDSECURE_REQUIRED',
		'Wymagane uwierzytelnienie 3-D Secure\nUwierzytelnienie 3-D Secure (Visa Secure/Mastercard Identity Check) jest było wymagane do zaakceptowania tej płatności, ale nie można go było uzyskać. Spróbuj ponownie za chwilę lub skontaktuj się ze swoim bankiem.\n',
	],
	[
		'ERROR_TDSECURE_FAILED',
		'Nieudane uwierzytelnienie 3-D Secure\nUwierzytelnienie 3-D Secure (Visa Secure/Mastercard Identity Check) jest wymagane, ale nie udało się go uzyskać. Spróbuj ponownie za chwilę lub skontaktuj się ze swoim bankiem.\n',
	],
]
