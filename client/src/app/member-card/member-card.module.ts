import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MemberCardRoutingModule } from './member-card-routing.module';
import { MemberCardComponent } from './member-card.component';
import {
	IgxIconModule,
	IgxNavbarModule,
	IgxRippleModule,
	IgxButtonModule
} from 'igniteui-angular';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
@NgModule({
    declarations: [
        MemberCardComponent
    ],
    imports: [
        CommonModule,
        MemberCardRoutingModule,
        IgxIconModule,
        IgxNavbarModule,
        IgxRippleModule,
        IgxButtonModule
    ]
})
export class MemberCardModule {
}
