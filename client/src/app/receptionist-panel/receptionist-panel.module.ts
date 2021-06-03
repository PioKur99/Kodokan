import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReceptionistPanelRoutingModule } from './receptionist-panel-routing.module';
import { ReceptionistPanelComponent } from './receptionist-panel.component';
import {
    IgxRippleModule,
    IgxButtonModule,
    IgxBottomNavModule,
    IgxIconModule,
    IgxDialogModule,
    IgxInputGroupModule,
    IgxToggleModule,
    IgxDropDownModule,
    IgxCheckboxModule,
    IgxGridModule,
    IgxRadioModule,
    IgxSelectModule,
    IgxSwitchModule
} from 'igniteui-angular';
import { CustomersComponent } from './customers/customers.component';
import { EntranceComponent } from './entrance/entrance.component';
import { MembershipCardsComponent } from './membership-cards/membership-cards.component';
import { PassesComponent } from './passes/passes.component';
import { AddAClientComponent } from './add-a-client/add-a-client.component';
import { EntryRegistrationComponent } from './entry-registration/entry-registration.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
@NgModule({
    declarations: [
        ReceptionistPanelComponent,
        CustomersComponent,
        EntranceComponent,
        MembershipCardsComponent,
        PassesComponent,
        AddAClientComponent,
        EntryRegistrationComponent
    ],
    imports: [
        CommonModule,
        ReceptionistPanelRoutingModule,
        IgxRippleModule,
        IgxButtonModule,
        IgxBottomNavModule,
        IgxIconModule,
        //BrowserAnimationsModule,
        IgxDialogModule,
        IgxInputGroupModule,
        IgxToggleModule,
        IgxDropDownModule,
        IgxCheckboxModule,
        IgxGridModule,
        FormsModule,
        IgxRadioModule,
        IgxSelectModule,
        IgxSwitchModule
    ]
})
export class ReceptionistPanelModule {
}
