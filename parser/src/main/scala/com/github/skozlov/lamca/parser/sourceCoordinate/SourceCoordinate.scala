package com.github.skozlov.lamca.parser.sourceCoordinate

case class SourceCoordinate(line: Int, char: Int) extends Ordered[SourceCoordinate]{
	require(line >= 0, s"Line $line is negative")
	require(char >= 0, s"Character index $char is negative")

	override def compare(that: SourceCoordinate): Int = {
		if (this.line == that.line){
			this.char compare that.char
		} else {
			this.line compare that.line
		}
	}

	def toNextChar: SourceCoordinate = this.copy(char = char + 1)

	def toNextLine: SourceCoordinate = SourceCoordinate(line = line + 1, char = 0)
}