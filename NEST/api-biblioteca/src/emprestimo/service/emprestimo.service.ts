import { Injectable } from '@nestjs/common';
import { EmprestimoDto } from '../dto/emprestimo.dto';
import { EmprestimoEntity } from '../entity/emprestimo.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';

@Injectable()
export class EmprestimoService {
  constructor(
    @InjectRepository(EmprestimoEntity)
    private emprestimoRepository: Repository<EmprestimoEntity>,
  ) {}

  create(emprestimo: EmprestimoDto): Promise<EmprestimoDto> {
    return this.emprestimoRepository.save(emprestimo);
  }

  findAll(): Promise<EmprestimoDto[]> {
    return this.emprestimoRepository.find();
  }
}
