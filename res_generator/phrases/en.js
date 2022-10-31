'use strict'

module.exports = [
	['POPUP_HEADER_PAYMENT', 'Payment'],
	['POPUP_HEADER_SUBSEQUENT', 'Sign up'],
	['CARD_NUMBER', 'Card number'],
	['CARD_EXPIRY', 'Expiry month/year'],
	['CARD_CODE', 'CVC'],
	['PAY', 'Pay'],
	['PAYING', 'Paying…'],
	['SAVE_CARD', 'Save card'],
	['SAVING', 'Saving…'],
	['POWERED_BY', 'Secure payment by Paylike'],
	['TDS_FULL_SCREEN_TEXT', 'Trouble seeing the window?'],
	['TDS_FULL_SCREEN_LINK', 'Expand to full-screen.'],
	[
		'ERROR_TIMEOUT',
		'Unexpected issue\nCould there be an issue with your internet connection? Please try again a little later.',
	],
	[
		'ERROR_RATE_LIMIT',
		'Unexpected issue\nWe are currently receiving too many payment requests. Please wait a few minutes before trying again.',
	],
	[
		'ERROR_UNEXPECTED',
		'Unexpected problem\nPlease try again a little later or contact our support with the message: {{message}}.',
	],
	[
		'ERROR_PUBLIC_KEY_IS_NOT_TEST',
		'The payment popup is in test mode, but the public key provided is for a live account. Please use a public key for a test account.',
	],
	[
		'ERROR_PUBLIC_KEY_IS_TEST',
		'The payment popup is in live mode, but the public key provided is for a test account. Please use a public key for a live account.',
	],
	[
		'ERROR_PUBLIC_KEY_UNKNOWN',
		'The provided public key is not associated with a Paylike account. Update the public key and try again.',
	],
	[
		'ERROR_PUBLIC_KEY_PAYMENTS_NOT_ALLOWED',
		'The public key is not allowed to accept payments.',
	],
	[
		'ERROR_INVALID_CARD_NUMBER',
		'Invalid card number\nPlease check the card number. If the problem persists, please contact your bank.',
	],
	[
		'ERROR_CARD_SCHEME_UNKNOWN',
		'Invalid card number or card not supported\nThe card number is incorrect or the card brand is not supported. Supported cards: {{supported}}.',
	],
	[
		'ERROR_CARD_SCHEME_UNSUPPORTED',
		'{{scheme}} cards are not supported\n{{scheme}} cards are unfortunately not supported. Please use one of: {{supported}}.',
	],
	[
		'ERROR_CARD_SECURITY_CODE_INVALID',
		'Invalid CVC (security code)\nPlease check the CVC (security code), typically found on the back of your card. If the problem persists, please contact your bank.',
	],
	[
		'ERROR_CARD_EXPIRED',
		'The card has expired\nYour card has expired. Please try another one.',
	],
	[
		'ERROR_CARD_DISABLED',
		'The card is blocked\nThis card is blocked, please contact your bank for further information.',
	],
	[
		'ERROR_CARD_LOST',
		'Card blocked\nThe card has been listed as lost or stolen, please contact your bank for further information.',
	],
	[
		'ERROR_AMOUNT_LIMIT',
		'Card amount limit exceeded\nThe amount limit on your card has been reached. Please contact your bank to reset or increase the limit.',
	],
	[
		'ERROR_INSUFFICIENT_FUNDS',
		'Insufficient funds\nThere are not enough funds available to cover the purchase. Please try another card or add funds to your account. If the problem persists, please contact your bank.',
	],
	[
		'ERROR_RECEIVER_BLOCKED',
		'Merchant blocked\nThe payment was rejected by your bank due to the merchant being blocked. Please contact your bank.',
	],
	[
		'ERROR_REJECTED_BY_ISSUER',
		'The card was rejected\nThe card has been rejected by your bank. Please contact your bank.',
	],
	[
		'ERROR_REJECTED',
		'The payment was rejected\nPlease try again in a little while or contact our support if the problem persists.',
	],
	[
		'ERROR_TDSECURE_REQUIRED',
		'3-D Secure required\n3-D Secure (Visa Secure/Mastercard Identity Check) was required to accept this payment, but it was not possible to obtain. Try again in a little while or contact your bank.',
	],
	[
		'ERROR_TDSECURE_FAILED',
		'3-D Secure failed\n3-D Secure (Visa Secure/Mastercard Identity Check) is required but failed to obtain authentication. Please try again or contact your bank.',
	],
]
