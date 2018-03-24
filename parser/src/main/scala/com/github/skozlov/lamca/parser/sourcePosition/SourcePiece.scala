package com.github.skozlov.lamca.parser.sourcePosition

case class SourcePiece(start: SourcePosition, end: SourcePosition){
	require(end >= start, s"End of piece ($end) is before start ($start)")
}

object SourcePiece{
	implicit def fromPosition(position: SourcePosition): SourcePiece = SourcePiece(start = position, end = position)
}