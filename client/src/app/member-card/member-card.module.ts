import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MemberCardRoutingModule } from './member-card-routing.module';
import { MemberCardComponent } from './member-card.component';
import {
	IgxIconModule,
	IgxNavbarModule,
	IgxRippleModule,
	IgxButtonModule,
	IgxCheckboxModule
} from 'igniteui-angular';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MessageComponent } from './message/message.component';
import { ChooseDisciplineComponent } from './choose-discipline/choose-discipline.component';
import { PreparedCardComponent } from './prepared-card/prepared-card.component';
import { FormsModule } from '@angular/forms';
@NgModule({
    declarations: [
        MemberCardComponent,
        MessageComponent,
        ChooseDisciplineComponent,
        PreparedCardComponent
    ],
    imports: [
        CommonModule,
        MemberCardRoutingModule,
        IgxIconModule,
        IgxNavbarModule,
        IgxRippleModule,
        IgxButtonModule,
        FormsModule,
        IgxCheckboxModule
    ]
})
export class MemberCardModule {
}
