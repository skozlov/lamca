package com.github.skozlov.lamca.parser

import com.github.skozlov.lamca.commons.localization.LocalizableMessage
import com.github.skozlov.lamca.parser.ParsingException._
import com.github.skozlov.lamca.parser.sourcePosition.{SourcePosition, SourcePiece}

case class ParsingException(message: SourceMessage) extends RuntimeException(serializeMessage(message))

object ParsingException{
	private def serializeMessage(message: SourceMessage): String = {
		s"Parsing error (from line ${message.piece.start.line} character ${message.piece.start.charIndex}" +
			s" to line ${message.piece.end.line} char ${message.piece.end.charIndex}): ${message.message.resolved()}"
	}

	def apply(message: LocalizableMessage, position: SourcePosition): ParsingException =
		ParsingException(SourceMessage(message, position))

	def apply(message: LocalizableMessage, from: SourcePosition, to: SourcePosition): ParsingException =
		ParsingException(SourceMessage(message, SourcePiece(from, to)))
}