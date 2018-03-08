package com.github.skozlov.lamca.parser.block

import com.github.skozlov.lamca.parser.ParsingContext

case class ParsingResult[+T](payload: T, finalContext: ParsingContext)