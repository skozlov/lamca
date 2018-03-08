package com.github.skozlov.lamca.parser

import com.github.skozlov.lamca.core.Message
import com.github.skozlov.lamca.parser.ParsingException._
import com.github.skozlov.lamca.parser.sourceCoordinate.{SourceCoordinate, SourcePiece}

case class ParsingException(message: SourceMessage) extends RuntimeException(serializeMessage(message))

object ParsingException{
	private def serializeMessage(message: SourceMessage): String = {
		s"Parsing error (from line ${message.piece.start.line} character ${message.piece.start.char}" +
			s" to line ${message.piece.end.line} char ${message.piece.end.char}): ${message.message.resolved}"
	}

	def apply(message: Message, position: SourceCoordinate): ParsingException =
		ParsingException(SourceMessage(message, position))

	def apply(message: Message, from: SourceCoordinate, to: SourceCoordinate): ParsingException =
		ParsingException(SourceMessage(message, SourcePiece(from, to)))
}