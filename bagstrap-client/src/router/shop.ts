export default [
  {
    path: '/shop',
    component: () => import('@/views/shop/ShopLayout.vue'),
    children: [
      {
        path: '',
        name: 'shop-list',
        component: () => import('@/views/shop/ShopView.vue'),
      },
    ],
  },
]
