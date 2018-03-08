package com.github.skozlov.lamca.parser.block

object Characters{
	val AbstractionStart = '\\'

	val AbstractionVariablesAndBodyDelimiter = '.'

	val OpeningBracket = '('

	val ClosingBracket = ')'

	def canBeFirstInVariable(char: Char): Boolean = {
		char == '_' || ('a' to 'z' contains char) || ('A' to 'Z' contains char)
	}

	def isValidInVariable(char: Char): Boolean = canBeFirstInVariable(char) || ('0' to '9' contains char)
}