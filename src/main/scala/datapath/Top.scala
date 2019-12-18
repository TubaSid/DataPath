package datapath
import chisel3._
class Top extends Module{
    val io=IO(new Bundle{

        val output=Output(SInt(32.W))
    })

    val control = Module(new Control())
    val reg_file = Module(new RegFile())
    val alu = Module(new Alu())
    val alu_control = Module(new AluControl())
    val imm_gen = Module(new ImmediateGeneration())
    val jalr = Module(new Jalr())
    val pc = Module(new Pc())
    val imem = Module(new InstructionMemory())
    val dmem = Module(new DataMemory())
    //pc
    imem.io.write_add:=pc.io.pc_out(21,2)
    imm_gen.io.pc:=pc.io.pc_out
    //imem
    control.io.opcode:=imem.io.read_data(6,0)
    reg_file.io.readreg1:=imem.io.read_data(19,15)
    reg_file.io.readreg2:=imem.io.read_data(24,20)
    reg_file.io.writereg:=imem.io.read_data(11,7)
    imm_gen.io.insn:=imem.io.read_data
    alu_control.io.func3:=imem.io.read_data(14,12)
    alu_control.io.func7:=imem.io.read_data(30)
    //control
    alu_control.io.aluop:=control.io.aluop
    jalr.io.b:=reg_file.io.readdata1
    dmem.io.datawrite:=reg_file.io.readdata2
    reg_file.io.regwrite:=control.io.regwrite
    
    //alucontrol
    alu.io.alucontrol:=alu_control.io.alucntrl

    //alu
    dmem.io.address:=alu.io.out(9,2)

    //dmem

   dmem.io.memwrite:=control.io.memwrite
   dmem.io.memread:=control.io.memread
 
    //mux2
    when(control.io.next_pc_sel==="b00".U)
    {
        pc.io.pc_in:=pc.io.pc_4

    }
    .elsewhen(control.io.next_pc_sel==="b01".U)
    {//mux3
     //and
        when((control.io.branch===1.U )&& (alu.io.branch===1.U))
        {
            pc.io.pc_in:=imm_gen.io.sb_imm
        }
        .otherwise
        {
            pc.io.pc_in:=pc.io.pc_4
        }

    }
    .elsewhen(control.io.next_pc_sel==="b10".U)
    {
        pc.io.pc_in:=imm_gen.io.uj_imm

    }
   .otherwise
    {
        pc.io.pc_in:=jalr.io.out

    }
    //mux4
    when(control.io.operand_a_sel==="b00".U)
    {
        alu.io.a:=reg_file.io.readdata1
    }
    .elsewhen(control.io.operand_a_sel==="b01".U)
    {
        alu.io.a:=pc.io.pc_out
    }
    .elsewhen(control.io.operand_a_sel==="b10".U)
    {
        alu.io.a:=pc.io.pc_4
    }
\    .otherwise
    {
        alu.io.a:=reg_file.io.readdata1
    }
    //mux6
    when(control.io.memtoreg==="b0".U)
    {
        reg_file.io.writedata:=alu.io.out
    }
   .otherwise
    {
        reg_file.io.writedata:=dmem.io.dataread
    }
    //mux5
    when(control.io.operand_b_sel==="b0".U)
    {
        alu.io.b:=reg_file.io.readdata2
        jalr.io.a:=reg_file.io.readdata2
    }
\    .otherwise
    {
        when(control.io.extend_sel==="b00".U)
        {
            alu.io.b:=imm_gen.io.i_imm
            jalr.io.a:=imm_gen.io.i_imm
        }
        .elsewhen(control.io.extend_sel==="b01".U)
        {
            alu.io.b:=imm_gen.io.s_imm
            jalr.io.a:=imm_gen.io.s_imm
        }
       .otherwise
        {
            alu.io.b:=imm_gen.io.u_imm
            jalr.io.a:=imm_gen.io.u_imm
        }
    }

    io.output := reg_file.io.readdata1

}
