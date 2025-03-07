// import { NgModule } from '@angular/core';
// import { RouterModule, Routes } from '@angular/router';
// import { FoodCatalogComponent } from './components/food-catalog.component';

// const routes: Routes = [
//   {
//     path: 'food-catalog/:id', 
//     component: FoodCatalogComponent,
    
//     data: {
//       renderMode: 'client-only' // 或 'server-only'
//     }
//   }
// ];

// @NgModule({
//   imports: [RouterModule.forChild(routes)],
//   exports: [RouterModule]
// })
// export class FoodCatalogRoutingModule { }
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FoodCatalogComponent } from './components/food-catalog.component';

const routes: Routes = [
  {
    path: 'food-catalog/:id', 
    component: FoodCatalogComponent,
    data: {
      prerender: false // 尝试这个属性
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FoodCatalogRoutingModule { }