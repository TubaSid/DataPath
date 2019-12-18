package datapath
import chisel3.iotesters.PeekPokeTester
class ControlDecode_t(c: ControlDecode ) extends PeekPokeTester(c){
poke(c.io.rtype,1)
poke(c.io.loadtype,0)
poke(c.io.stype,0)
poke(c.io.sbtype,0)
poke(c.io.itype,0)
poke(c.io.jalrtype,0)
poke(c.io.jaltype,0)
poke(c.io.luitype,0)

expect(c.io.memwrite,0)
expect(c.io.branch,0)
expect(c.io.memread,0)
expect(c.io.regwrite,1)
expect(c.io.memtoreg,0)
expect(c.io.aluop,0)
expect(c.io.operand_a_sel,0)
expect(c.io.operand_b_sel,0)
expect(c.io.extend_sel,0)
expect(c.io.next_pc_sel,0)
}