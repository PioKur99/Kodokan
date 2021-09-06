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
    IgxDropDownModule,
    IgxCheckboxModule,
    IgxInputGroupModule,
    IgxToggleModule
} from 'igniteui-angular';
import { CustomersComponent } from './customers/customers.component';
import { EntranceComponent } from './entrance/entrance.component';
import { MembershipCardsComponent } from './membership-cards/membership-cards.component';
import { PassesComponent } from './passes/passes.component';
import { AddAClientComponent } from './add-a-client/add-a-client.component';
import { EntryRegistrationComponent } from './entry-registration/entry-registration.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CustomerDataComponent } from './customer-data/customer-data.component';
import { CustomerEditDataComponent } from './customer-edit-data/customer-edit-data.component';
import { FormsModule } from '@angular/forms';
import { CustomerFamilyComponent } from './customer-family/customer-family.component';
import { CustomerAddPassComponent } from './customer-add-pass/customer-add-pass.component';
import { CustomerShowPassesComponent } from './customer-show-passes/customer-show-passes.component';
import { CustomerShowEntrancesComponent } from './customer-show-entrances/customer-show-entrances.component';
import { CustomerSendMailComponent } from './customer-send-mail/customer-send-mail.component';
import { WebcamModule } from 'ngx-webcam';

@NgModule({
    declarations: [
        ReceptionistPanelComponent,
        CustomersComponent,
        EntranceComponent,
        MembershipCardsComponent,
        PassesComponent,
        AddAClientComponent,
        EntryRegistrationComponent,
        CustomerDataComponent,
        CustomerEditDataComponent,
        CustomerFamilyComponent,
        CustomerAddPassComponent,
        CustomerShowPassesComponent,
        CustomerShowEntrancesComponent,
        CustomerSendMailComponent
    ],
    imports: [
        CommonModule,
        ReceptionistPanelRoutingModule,
        IgxRippleModule,
        IgxButtonModule,
        WebcamModule,
        IgxBottomNavModule,
        FormsModule,
        IgxIconModule,
        //BrowserAnimationsModule,
        IgxDialogModule,
        IgxDropDownModule,
        IgxCheckboxModule,
        IgxInputGroupModule,
        IgxToggleModule
    ]
})
export class ReceptionistPanelModule {
}
