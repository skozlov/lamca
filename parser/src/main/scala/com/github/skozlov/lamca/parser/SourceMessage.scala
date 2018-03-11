package com.github.skozlov.lamca.parser

import com.github.skozlov.lamca.commons.localization.LocalizableMessage
import com.github.skozlov.lamca.parser.sourceCoordinate.{SourceCoordinate, SourcePiece}

case class SourceMessage(message: LocalizableMessage, piece: SourcePiece)

object SourceMessage{
	def apply(message: LocalizableMessage, position: SourceCoordinate): SourceMessage =
		SourceMessage(message, SourcePiece(start = position, end = position))
}