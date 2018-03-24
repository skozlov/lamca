package com.github.skozlov.lamca.parser

import com.github.skozlov.lamca.parser.sourcePosition.SourcePosition

case class ParsingContext(sourceLines: List[String], currentPosition: SourcePosition){
	lazy val currentChar: Option[Char] = currentLine flatMap {
		line => if (currentPosition.charIndex < line.length) Some(line(currentPosition.charIndex)) else None
	}

	def currentLine: Option[String] = sourceLines.headOption

	lazy val isCompleted: Boolean = currentLine.isEmpty

	def rightShift: ParsingContext = this.copy(currentPosition = currentPosition.toNextChar)

	def toNextLine: ParsingContext = ParsingContext(
		sourceLines = sourceLines drop 1,
		currentPosition = currentPosition.toNextLine
	)
}