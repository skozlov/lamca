package com.github.skozlov.lamca.parser

import com.github.skozlov.lamca.core.Message
import com.github.skozlov.lamca.parser.sourceCoordinate.{SourceCoordinate, SourcePiece}

case class SourceMessage(message: Message, piece: SourcePiece)

object SourceMessage{
	def apply(message: Message, position: SourceCoordinate): SourceMessage =
		SourceMessage(message, SourcePiece(start = position, end = position))
}