package com.github.skozlov.lamca.parser

import com.github.skozlov.lamca.core.Message

object Messages{
	private val keyPrefix = "com.github.skozlov.lamca.parser"

	lazy val MissingAbstractionVariables: Message = Message(
		key = s"$keyPrefix.abstraction.variables.missing",
		defaultTemplate = "Missing abstraction variables"
	)

	lazy val MissingAbstractionBody: Message = Message(
		key = s"$keyPrefix.abstraction.body.missing",
		defaultTemplate = "Missing abstraction body"
	)

	def missingCharacter(char: Char): Message = Message(
		key = s"$keyPrefix.missingCharacter",
		defaultTemplate = "Missing ${char}",
		parameters = Map("char" -> char)
	)

	def unexpectedCharacter(expected: Char): Message = Message(
		key = s"$keyPrefix.unexpectedCharacter",
		defaultTemplate = "Expected ${char}",
		parameters = Map("char" -> expected)
	)

	lazy val EmptyTerm: Message = Message(
		key = s"$keyPrefix.term.empty",
		defaultTemplate = "Empty term"
	)
}