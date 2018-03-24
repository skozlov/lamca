package com.github.skozlov.lamca.parser

import com.github.skozlov.lamca.commons.localization.LocalizableMessage
import com.github.skozlov.lamca.parser.sourcePosition.SourcePiece

case class SourceMessage(message: LocalizableMessage, piece: SourcePiece)