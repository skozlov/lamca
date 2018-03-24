package com.github.skozlov.lamca.parser.sourcePosition

case class SourcePosition(line: Int, charIndex: Int) extends Ordered[SourcePosition]{
	require(line >= 0, s"Line $line is negative")
	require(charIndex >= 0, s"Character index $charIndex is negative")

	override def compare(that: SourcePosition): Int = {
		if (this.line == that.line){
			this.charIndex compare that.charIndex
		} else {
			this.line compare that.line
		}
	}

	def toNextChar: SourcePosition = this.copy(charIndex = charIndex + 1)

	def toNextLine: SourcePosition = SourcePosition(line = line + 1, charIndex = 0)
}