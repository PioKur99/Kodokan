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
	IgxInputGroupModule
} from 'igniteui-angular';
import { CustomersComponent } from './customers/customers.component';
import { EntranceComponent } from './entrance/entrance.component';
import { MembershipCardsComponent } from './membership-cards/membership-cards.component';
import { PassesComponent } from './passes/passes.component';
import { AddAClientComponent } from './add-a-client/add-a-client.component';
import { EntryRegistrationComponent } from './entry-registration/entry-registration.component';
import { FormsModule } from '@angular/forms';
import { WebcamModule } from 'ngx-webcam';
import { ImageCropperModule } from 'ngx-image-cropper';
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
        ImageCropperModule,
        WebcamModule,
        CommonModule,
        ReceptionistPanelRoutingModule,
        IgxRippleModule,
        IgxButtonModule,
        IgxBottomNavModule,
        IgxIconModule,
        IgxDialogModule,
        FormsModule,
        IgxInputGroupModule
    ]
})
export class ReceptionistPanelModule {
}
