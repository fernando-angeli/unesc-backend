import { Body, Controller, Post, Get } from '@nestjs/common';
import { EmprestimoService } from '../service/emprestimo.service';
import { EmprestimoDto } from '../dto/emprestimo.dto';

@Controller('emprestimo')
export class EmprestimoController {
  constructor(private emprestimoService: EmprestimoService) {}

  @Post()
  create(@Body() emprestimo: EmprestimoDto): Promise<EmprestimoDto> {
    return this.emprestimoService.create(emprestimo);
  }

  @Get()
  async findAll(): Promise<EmprestimoDto[]> {
    return this.emprestimoService.findAll();
  }
}
