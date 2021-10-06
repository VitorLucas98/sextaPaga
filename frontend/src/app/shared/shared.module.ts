import { NgModule } from '@angular/core';
import { MensagemComponent } from '../components/mensagem/mensagem.component';
import { AtivoInativoPipe } from '../pipes/ativo-inativo.pipe';
import { FormataDataPipe } from '../pipes/formata-data.pipe';
import { PRIMENG_IMPORTS } from './primeng-imports';

@NgModule({
    declarations : [
        AtivoInativoPipe,
        MensagemComponent,
        FormataDataPipe
    ],
    imports: [
        PRIMENG_IMPORTS,
    ],
    providers: [FormataDataPipe],
    exports: [
        PRIMENG_IMPORTS,
        MensagemComponent,
        AtivoInativoPipe,
        FormataDataPipe
    ]
})
export class SharedModule { }
