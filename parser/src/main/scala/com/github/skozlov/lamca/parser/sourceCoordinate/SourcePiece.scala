package com.github.skozlov.lamca.parser.sourceCoordinate

case class SourcePiece(start: SourceCoordinate, end: SourceCoordinate){
	require(end >= start, s"End of piece ($end) is before start ($start)")
}

object SourcePiece{
	def apply(coordinate: SourceCoordinate): SourcePiece = SourcePiece(start = coordinate, end = coordinate)
}