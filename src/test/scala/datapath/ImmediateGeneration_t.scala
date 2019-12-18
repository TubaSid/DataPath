package datapath
import chisel3.iotesters.PeekPokeTester
class ImmediateGeneration_t(c:ImmediateGeneration) extends PeekPokeTester(c){
    //poke(c.io.insn,3146003)
poke(c.io.insn,8390035)
    poke(c.io.pc,0)
    expect(c.io.i_imm,8)
}