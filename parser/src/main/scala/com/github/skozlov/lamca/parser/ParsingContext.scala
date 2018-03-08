package com.github.skozlov.lamca.parser

import com.github.skozlov.lamca.parser.sourceCoordinate.SourceCoordinate

case class ParsingContext(sourceLines: List[String], currentPosition: SourceCoordinate){
	lazy val currentChar: Option[Char] = currentLine flatMap {
		line => if (currentPosition.char < line.length) Some(line(currentPosition.char)) else None
	}

	def currentLine: Option[String] = sourceLines.headOption

	lazy val isCompleted: Boolean = currentLine.isEmpty

	def rightShift: ParsingContext = this.copy(currentPosition = currentPosition.toNextChar)

	def toNextLine: ParsingContext = ParsingContext(
		sourceLines = sourceLines drop 1,
		currentPosition = currentPosition.toNextLine
	)
}