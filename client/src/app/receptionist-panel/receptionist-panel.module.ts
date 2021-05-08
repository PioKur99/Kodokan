import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReceptionistPanelRoutingModule } from './receptionist-panel-routing.module';
import { ReceptionistPanelComponent } from './receptionist-panel.component';
//import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
    IgxRippleModule,
    IgxButtonModule,
    IgxBottomNavModule,
    IgxIconModule
} from 'igniteui-angular';
import { CustomersComponent } from './customers/customers.component';

@NgModule({
    declarations: [
        ReceptionistPanelComponent,
        CustomersComponent
    ],
    imports: [
        CommonModule,
        ReceptionistPanelRoutingModule,
        //BrowserAnimationsModule,
        IgxRippleModule,
        IgxButtonModule,
        IgxBottomNavModule,
        IgxIconModule
    ]
})
export class ReceptionistPanelModule {
}
