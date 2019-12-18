package datapath
import chisel3._
class Alu extends Module{
    val io=IO(new Bundle{
        val a=Input(SInt(32.W))
        val b=Input(SInt(32.W))
        val alucontrol=Input(UInt(5.W))
        val branch=Output(UInt(1.W))
        val out=Output(SInt(32.W))
    })
    //add,addi
    when(io.alucontrol==="b00000".U)
    {
        io.out:=io.a+io.b
    }
    //slt.slti
    .elsewhen(io.alucontrol==="b00010".U)
    {
        //io.out:=io.a<<io.b(4,0)
        when(io.a<io.b)
        {
            io.out:=1.S
        }
        .otherwise
        {
            io.out:=0.S
        }

    }
    //beq
    .elsewhen(io.alucontrol==="b10000".U)
    {
        when(io.a === io.b) 
        {
            io.out := 1.S
        } 
        .otherwise 
        {
            io.out := 0.S
        }
    }
    //bge
    .elsewhen(io.alucontrol==="b10101".U)
    {
        when(io.a>=io.b)
        {
          io.out := 1.S
        }
        .otherwise
        {
          io.out := 0.S
        }
    }


    //xor,xori
    .elsewhen(io.alucontrol==="b00100".U)
    {
        io.out := io.a ^ io.b
    }
    //sll,slli
    .elsewhen(io.alucontrol==="b00001".U){
        io.out:=io.a<<io.b(4,0)

    }
    //srl,srli
    .elsewhen(io.alucontrol==="b00101".U)
    {
      when((io.a >=io.b(4,3).asSInt)) 
      {
        io.out := 1.S
      }
    .otherwise
        {
            io.out := 0.S
        }
    }
    //or,ori
    .elsewhen(io.alucontrol==="b00110".U)
    {
        io.out := io.a | io.b
    }
    //and,andi
    .elsewhen(io.alucontrol==="b00111".U)
    {
        io.out:= io.a & io.b
        
    }
    //sub
    .elsewhen(io.alucontrol==="b01000".U)
    {
     io.out := io.a - io.b
    }
    //bne
    .elsewhen(io.alucontrol==="b10001".U)
    {
      when(io.a=/=io.b)
        {
         io.out := 1.S
        }
        .otherwise 
        {
         io.out := 0.S
        }
    }
    //blt
    .elsewhen(io.alucontrol==="b10100".U)
    {
        when(io.a < io.b)
        {
         io.out := 1.S
        } 
        .otherwise 
        {
         io.out := 0.S
        }
    }
    //sra,srai
    .elsewhen(io.alucontrol==="b01101".U)
    {
     io.out := io.a >> io.b(4,0)
    }
       
    //sltui,sltu,bltu
    .elsewhen(io.alucontrol==="b00011".U)
    {
    when(io.a.asUInt < io.b.asUInt) 
        {
           io.out:= 1.S
        } 
     .otherwise 
        {
          io.out := 0.S
        }
        
    }
    //bgeu
    .elsewhen(io.alucontrol==="b10110".U)
    {
        when(io.a.asUInt >= io.b.asUInt)
        {
         io.out:= 1.S
        }
        .otherwise
        {
          io.out := 0.S
        }
    }
    //jal,jalr
    .elsewhen(io.alucontrol==="b11111".U)
    {
      io.out:=io.a
    }
    .otherwise
    {
        io.out:=DontCare
    }

    when(io.alucontrol(4,3)==="b10".U && io.out===1.S)
    {
        io.branch:=1.U
    }
    .otherwise{
        io.branch:=0.U
    }
}