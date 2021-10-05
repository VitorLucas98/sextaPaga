import { NgModule } from '@angular/core';
import { MensagemComponent } from '../components/mensagem/mensagem.component';
import { AtivoInativoPipe } from '../pipes/ativo-inativo.pipe';
import { PRIMENG_IMPORTS } from './primeng-imports';

@NgModule({
    declarations : [
        AtivoInativoPipe,
        MensagemComponent
    ],
    imports: [
        PRIMENG_IMPORTS,
    ],
    providers: [],
    exports: [
        PRIMENG_IMPORTS,
        MensagemComponent,
        AtivoInativoPipe
    ]
})
export class SharedModule { }
