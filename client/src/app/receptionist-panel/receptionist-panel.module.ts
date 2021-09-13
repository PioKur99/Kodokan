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
	IgxRadioModule,
	IgxToggleModule,
	IgxDropDownModule,
	IgxListModule,
    IgxGridModule,
    IgxComboModule,
    IgxCheckboxModule
} from 'igniteui-angular';
import { CustomersComponent } from './customers/customers.component';
import { EntranceComponent } from './entrance/entrance.component';
import { MembershipCardsComponent } from './membership-cards/membership-cards.component';
import { PassesComponent } from './passes/passes.component';
import { AddAClientComponent } from './add-a-client/add-a-client.component';
import { EntryRegistrationComponent } from './entry-registration/entry-registration.component';
import { FamilyMemberComponent } from './family-member/family-member.component';
import { FormsModule } from '@angular/forms';
import { WebcamModule } from 'ngx-webcam';
import { ImageCropperModule } from 'ngx-image-cropper';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PassDetailsComponent } from './pass-details/pass-details.component';
import { CustomerDataComponent } from './customer-data/customer-data.component';
import { CustomerEditDataComponent } from './customer-edit-data/customer-edit-data.component';
import { CustomerFamilyComponent } from './customer-family/customer-family.component';
import { CustomerAddPassComponent } from './customer-add-pass/customer-add-pass.component';
import { CustomerShowPassesComponent } from './customer-show-passes/customer-show-passes.component';
import { CustomerShowEntrancesComponent } from './customer-show-entrances/customer-show-entrances.component';
import { CustomerSendMailComponent } from './customer-send-mail/customer-send-mail.component';
import { CustomerAddFamilyMemberComponent } from './customer-add-family-member/customer-add-family-member.component';

@NgModule({
    declarations: [
        ReceptionistPanelComponent,
        CustomersComponent,
        EntranceComponent,
        MembershipCardsComponent,
        PassesComponent,
        AddAClientComponent,
        EntryRegistrationComponent,
        FamilyMemberComponent,
        PassDetailsComponent,
        CustomerDataComponent,
        CustomerEditDataComponent,
        CustomerFamilyComponent,
        CustomerAddPassComponent,
        CustomerShowPassesComponent,
        CustomerShowEntrancesComponent,
        CustomerSendMailComponent,
        CustomerAddFamilyMemberComponent
    ],
    imports: [
        ImageCropperModule,
        WebcamModule,
        CommonModule,
        ReceptionistPanelRoutingModule,
        IgxRippleModule,
        IgxButtonModule,
        WebcamModule,
        IgxBottomNavModule,
        FormsModule,
        IgxIconModule,
        IgxDialogModule,
        FormsModule,
        IgxInputGroupModule,
        IgxRadioModule,
        IgxToggleModule,
        IgxDropDownModule,
        IgxListModule,
        IgxDialogModule,
        IgxGridModule,
        IgxComboModule,
        IgxDropDownModule,
        IgxCheckboxModule,
        IgxInputGroupModule,
        IgxToggleModule
    ]
})
export class ReceptionistPanelModule {
}
