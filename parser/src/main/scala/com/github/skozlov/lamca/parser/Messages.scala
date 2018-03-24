package com.github.skozlov.lamca.parser

import com.github.skozlov.lamca.commons.localization.LocalizableMessage

object Messages{
	private val keyPrefix = "com.github.skozlov.lamca.parser"

	val MissingAbstractionVariables = LocalizableMessage(
		key = s"$keyPrefix.abstraction.variables.missing",
		defaultTemplate = "Missing abstraction variables"
	)

	val MissingAbstractionBody = LocalizableMessage(
		key = s"$keyPrefix.abstraction.body.missing",
		defaultTemplate = "Missing abstraction body"
	)

	def missingCharacter(char: Char) = LocalizableMessage(
		key = s"$keyPrefix.missingCharacter",
		defaultTemplate = "Missing ${char}",
		parameters = Map("char" -> char)
	)

	def unexpectedCharacter(expected: Char) = LocalizableMessage(
		key = s"$keyPrefix.unexpectedCharacter",
		defaultTemplate = "Expected ${char}",
		parameters = Map("char" -> expected)
	)

	val EmptyTerm = LocalizableMessage(
		key = s"$keyPrefix.term.empty",
		defaultTemplate = "Empty term"
	)
}