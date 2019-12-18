package datapath
import chisel3.iotesters.PeekPokeTester
class InstructionTypeDecode_t(c:InstructionTypeDecode) extends PeekPokeTester(c){
    poke(c.io.opcode,51)
    expect(c.io.rtype,1)
    expect(c.io.loadtype,0)
    expect(c.io.stype,0)
    expect(c.io.sbtype,0)
    expect(c.io.itype,0)
    expect(c.io.jalrtype,0)
    expect(c.io.jaltype,0)
    expect(c.io.luitype,0)
    

}