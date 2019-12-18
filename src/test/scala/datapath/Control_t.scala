package datapath
import chisel3.iotesters.PeekPokeTester
class Control_t(c:Control)extends PeekPokeTester(c){
    poke(c.io.opcode,51)

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
