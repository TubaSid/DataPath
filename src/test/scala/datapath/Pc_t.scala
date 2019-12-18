package datapath
import chisel3.iotesters.PeekPokeTester

class Pc_t(c: Pc) extends PeekPokeTester(c) {
    poke(c.io.pc_in, 0)
    poke(c.io.pc_in, 4)
    poke(c.io.pc_in, 8)
   
}